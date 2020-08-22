/*
 * Copyright (c) 2017-2020. Nitrite author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dizitart.no2.rocksdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.collection.Document;
import org.dizitart.no2.collection.NitriteCollection;
import org.dizitart.no2.collection.UpdateOptions;
import org.dizitart.no2.common.SortOrder;
import org.dizitart.no2.common.concurrent.ThreadPoolManager;
import org.dizitart.no2.exceptions.NitriteIOException;
import org.dizitart.no2.exceptions.ValidationException;
import org.dizitart.no2.index.IndexOptions;
import org.dizitart.no2.index.IndexType;
import org.dizitart.no2.mapper.Mappable;
import org.dizitart.no2.mapper.NitriteMapper;
import org.dizitart.no2.repository.ObjectRepository;
import org.dizitart.no2.repository.annotations.Id;
import org.dizitart.no2.repository.annotations.Index;
import org.dizitart.no2.repository.annotations.Indices;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import static org.dizitart.no2.collection.Document.createDocument;
import static org.dizitart.no2.common.Constants.INTERNAL_NAME_SEPARATOR;
import static org.dizitart.no2.common.Constants.META_MAP_NAME;
import static org.dizitart.no2.filters.Filter.ALL;
import static org.dizitart.no2.filters.FluentFilter.where;
import static org.junit.Assert.*;

/**
 * @author Anindya Chatterjee.
 */
public class NitriteTest extends AbstractTest {

    private NitriteCollection collection;
    private SimpleDateFormat simpleDateFormat;

    @Before
    public void setUp() throws ParseException {
        db = TestUtil.createDb(fileName, "test-user", "test-password");

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

        Document doc1 = createDocument("firstName", "fn1")
            .put("lastName", "ln1")
            .put("birthDay", simpleDateFormat.parse("2012-07-01T16:02:48.440Z"))
            .put("data", new byte[]{1, 2, 3})
            .put("body", "a quick brown fox jump over the lazy dog");
        Document doc2 = createDocument("firstName", "fn2")
            .put("lastName", "ln2")
            .put("birthDay", simpleDateFormat.parse("2010-06-12T16:02:48.440Z"))
            .put("data", new byte[]{3, 4, 3})
            .put("body", "hello world from nitrite");
        Document doc3 = createDocument("firstName", "fn3")
            .put("lastName", "ln2")
            .put("birthDay", simpleDateFormat.parse("2014-04-17T16:02:48.440Z"))
            .put("data", new byte[]{9, 4, 8})
            .put("body", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed nunc mi, mattis ullamcorper dignissim vitae, condimentum non lorem.");

        collection = db.getCollection("test");
        collection.remove(ALL);

        collection.createIndex("body", IndexOptions.indexOptions(IndexType.Fulltext));
        collection.createIndex("firstName", IndexOptions.indexOptions(IndexType.Unique));
        collection.insert(doc1, doc2, doc3);
    }

    @After
    public void tearDown() {
        if (collection.isOpen()) {
            collection.remove(ALL);
            collection.close();
        }
    }

    @Test
    public void testListCollectionNames() {
        Set<String> collectionNames = db.listCollectionNames();
        assertEquals(collectionNames.size(), 1);
    }

    @Test
    public void testListRepositories() {
        db.getRepository(getClass());
        Set<String> repositories = db.listRepositories();
        assertEquals(repositories.size(), 1);
    }

    @Test
    public void testHasCollection() {
        assertTrue(db.hasCollection("test"));
        assertFalse(db.hasCollection("lucene" + INTERNAL_NAME_SEPARATOR + "test"));
    }

    @Test
    public void testHasRepository() {
        db.getRepository(getClass());
        assertTrue(db.hasRepository(getClass()));
        assertFalse(db.hasRepository(String.class));
    }


    @Test
    public void testReopen() throws ParseException {
        assertNotNull(db);
        NitriteCollection testCollection = db.getCollection("test");
        assertNotNull(testCollection);
        long prevSize = testCollection.find().size();

        db.close();

        db = null;

        db = TestUtil.createDb(fileName, "test-user", "test-password");

        assertNotNull(db);
        testCollection = db.getCollection("test");
        assertNotNull(testCollection);
        long sizeNow = testCollection.find().size();
        assertEquals(prevSize, sizeNow);

        db.close();
        db = null;
        db = TestUtil.createDb(fileName, "test-user", "test-password");

        testCollection = db.getCollection("test");
        testCollection.insert(createDocument("firstName", "fn12")
            .put("lastName", "ln12")
            .put("birthDay", simpleDateFormat.parse("2010-07-01T16:02:48.440Z"))
            .put("data", new byte[]{10, 20, 30})
            .put("body", "a quick brown fox jump over the lazy dog"));

        db.close();
        db = null;
        db = TestUtil.createDb(fileName, "test-user", "test-password");

        testCollection = db.getCollection("test");
        assertNotNull(testCollection);
        sizeNow = testCollection.find().size();
        assertEquals(prevSize + 1, sizeNow);
    }

    @Test
    public void testClose() {
        NitriteCollection testCollection = db.getCollection("test");
        testCollection.insert(createDocument("a", "b"));
        db.close();

        assertFalse(testCollection.isOpen());
    }

    @Test
    public void testGetCollection() {
        NitriteCollection collection = db.getCollection("test-collection");
        assertNotNull(collection);
        assertEquals(collection.getName(), "test-collection");
    }

    @Test
    public void testGetRepository() {
        ObjectRepository<NitriteTest> repository = db.getRepository(NitriteTest.class);
        assertNotNull(repository);
        assertEquals(repository.getType(), NitriteTest.class);
    }

    @Test
    public void testGetRepositoryWithKey() {
        ObjectRepository<NitriteTest> repository = db.getRepository(NitriteTest.class, "key");
        assertNotNull(repository);
        assertEquals(repository.getType(), NitriteTest.class);
        assertFalse(db.hasRepository(NitriteTest.class));
        assertTrue(db.hasRepository(NitriteTest.class, "key"));
    }

    @Test
    public void testMultipleGetCollection() {
        NitriteCollection collection = db.getCollection("test-collection");
        assertNotNull(collection);
        assertEquals(collection.getName(), "test-collection");

        NitriteCollection collection2 = db.getCollection("test-collection");
        assertNotNull(collection2);
        assertEquals(collection2.getName(), "test-collection");
    }

    @Test
    public void testMultipleGetRepository() {
        ObjectRepository<NitriteTest> repository = db.getRepository(NitriteTest.class);
        assertNotNull(repository);
        assertEquals(repository.getType(), NitriteTest.class);

        ObjectRepository<NitriteTest> repository2 = db.getRepository(NitriteTest.class);
        assertNotNull(repository2);
        assertEquals(repository2.getType(), NitriteTest.class);
    }

    @Test(expected = ValidationException.class)
    public void testGetRepositoryInvalid() {
        db.getRepository(null);
    }

    @Test(expected = NitriteIOException.class)
    public void testGetCollectionNullStore() {
        db = Nitrite.builder().openOrCreate();
        db.close();
        db.getCollection("test");
    }

    @Test(expected = NitriteIOException.class)
    public void testGetRepositoryNullStore() {
        db = Nitrite.builder().openOrCreate();
        db.close();
        db.getRepository(NitriteTest.class);
    }

    @Test(expected = NitriteIOException.class)
    public void testGetKeyedRepositoryNullStore() {
        db = Nitrite.builder().openOrCreate();
        db.close();
        db.getRepository(NitriteTest.class, "key");
    }

    @Test(expected = NitriteIOException.class)
    public void testCommitNullStore() {
        db = Nitrite.builder().openOrCreate();
        db.close();
        db.commit();
    }

    @Test(expected = NitriteIOException.class)
    public void testCloseNullStore() {
        try (Nitrite db = Nitrite.builder().openOrCreate()) {
            assertNotNull(db);
        }
    }

    @Test(expected = ValidationException.class)
    public void testGetCollectionInvalidName() {
        db.getCollection(META_MAP_NAME);
    }

    @Test
    public void testIssue185() throws InterruptedException {
        final ObjectRepository<Receipt> repository = db.getRepository(Receipt.class);
        final Receipt receipt = new Receipt();
        receipt.clientRef = "111-11111";
        receipt.status = Receipt.Status.PREPARING;
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            for (int i = 0; i < 1000; ++i) {
                try {
                    repository.update(receipt, true);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ignored) {
                    }
                    repository.remove(receipt);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ignored) {
                    }
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            latch.countDown();
        }).start();

        for (int i = 0; i < 1000; ++i) {
            repository.find(where("status").eq(Receipt.Status.COMPLETED).not())
                .sort("createdTimestamp", SortOrder.Descending).toList();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {
            }
        }
        latch.await();
    }

    @Test
    public void testIssue193() throws InterruptedException {
        final ObjectRepository<Receipt> repository = db.getRepository(Receipt.class);
        final PodamFactory factory = new PodamFactoryImpl();
        final String[] refs = new String[]{"1", "2", "3", "4", "5"};
        final Random random = new Random();
        ExecutorService pool = ThreadPoolManager.workerPool();

        final CountDownLatch latch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            pool.submit(() -> {
                int refIndex = random.nextInt(5);
                Receipt receipt = factory.manufacturePojoWithFullData(Receipt.class);
                receipt.setClientRef(refs[refIndex]);
                repository.update(receipt, true);
                latch.countDown();
            });
        }

        latch.await();
        assertTrue(repository.find().size() <= 5);
        pool.shutdown();
    }

    @Test
    public void testIssue212() {
        NitriteCollection collection = db.getCollection("testIssue212");
        Document doc1 = createDocument("key", "key").put("second_key", "second_key").put("third_key", "third_key");
        Document doc2 = createDocument("key", "key").put("second_key", "second_key").put("fourth_key", "fourth_key");
        Document doc = createDocument("fifth_key", "fifth_key");

        if (!collection.hasIndex("key")) {
            collection.createIndex("key", IndexOptions.indexOptions(IndexType.NonUnique));
        }
        if (!collection.hasIndex("second_key")) {
            collection.createIndex("second_key", IndexOptions.indexOptions(IndexType.NonUnique));
        }

        collection.insert(doc1, doc2);
        collection.update(where("key").eq("key").and(where("second_key").eq("second_key")),
            doc, UpdateOptions.updateOptions(true));

        for (Document document : collection.find()) {
            System.out.println(document);
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompatChild implements Mappable {
        private Long childId;
        private String lastName;

        @Override
        public Document write(NitriteMapper mapper) {
            return Document.createDocument("childId", childId)
                .put("lastName", lastName);
        }

        @Override
        public void read(NitriteMapper mapper, Document document) {
            childId = document.get("childId", Long.class);
            lastName = document.get("lastName", String.class);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Indices({
        @Index(value = "synced", type = IndexType.NonUnique)
    })
    public static class Receipt implements Mappable {
        private Status status;
        @Id
        private String clientRef;
        private Boolean synced;
        private Long createdTimestamp = System.currentTimeMillis();

        @Override
        public Document write(NitriteMapper mapper) {
            return createDocument("status", status)
                .put("clientRef", clientRef)
                .put("synced", synced)
                .put("createdTimestamp", createdTimestamp);
        }

        @Override
        public void read(NitriteMapper mapper, Document document) {
            if (document != null) {
                Object status = document.get("status");
                if (status instanceof Status) {
                    this.status = (Status) status;
                } else {
                    this.status = Status.valueOf(status.toString());
                }
                this.clientRef = document.get("clientRef", String.class);
                this.synced = document.get("synced", Boolean.class);
                this.createdTimestamp = document.get("createdTimestamp", Long.class);
            }
        }
        public enum Status {
            COMPLETED,
            PREPARING,
        }
    }
}
