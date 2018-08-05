/*
 *
 * Copyright 2018 Nitrite author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.dizitart.no2.fulltext.languages;

import org.dizitart.no2.fulltext.Language;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Greek stop words
 *
 * @since 2.1.0
 * @author Anindya Chatterjee
 */
public class Greek implements Language {
    @Override
    public Set<String> stopWords() {
        return new HashSet<>(Arrays.asList(
                "αλλα",
                "αν",
                "αντι",
                "απο",
                "αυτα",
                "αυτεσ",
                "αυτη",
                "αυτο",
                "αυτοι",
                "αυτοσ",
                "αυτουσ",
                "αυτων",
                "αἱ",
                "αἳ",
                "αἵ",
                "αὐτόσ",
                "αὐτὸς",
                "αὖ",
                "γάρ",
                "γα",
                "γα^",
                "γε",
                "για",
                "γοῦν",
                "γὰρ",
                "δ'",
                "δέ",
                "δή",
                "δαί",
                "δαίσ",
                "δαὶ",
                "δαὶς",
                "δε",
                "δεν",
                "δι'",
                "διά",
                "διὰ",
                "δὲ",
                "δὴ",
                "δ’",
                "εαν",
                "ειμαι",
                "ειμαστε",
                "ειναι",
                "εισαι",
                "ειστε",
                "εκεινα",
                "εκεινεσ",
                "εκεινη",
                "εκεινο",
                "εκεινοι",
                "εκεινοσ",
                "εκεινουσ",
                "εκεινων",
                "ενω",
                "επ",
                "επι",
                "εἰ",
                "εἰμί",
                "εἰμὶ",
                "εἰς",
                "εἰσ",
                "εἴ",
                "εἴμι",
                "εἴτε",
                "η",
                "θα",
                "ισωσ",
                "κ",
                "καί",
                "καίτοι",
                "καθ",
                "και",
                "κατ",
                "κατά",
                "κατα",
                "κατὰ",
                "καὶ",
                "κι",
                "κἀν",
                "κἂν",
                "μέν",
                "μή",
                "μήτε",
                "μα",
                "με",
                "μεθ",
                "μετ",
                "μετά",
                "μετα",
                "μετὰ",
                "μη",
                "μην",
                "μἐν",
                "μὲν",
                "μὴ",
                "μὴν",
                "να",
                "ο",
                "οι",
                "ομωσ",
                "οπωσ",
                "οσο",
                "οτι",
                "οἱ",
                "οἳ",
                "οἷς",
                "οὐ",
                "οὐδ",
                "οὐδέ",
                "οὐδείσ",
                "οὐδεὶς",
                "οὐδὲ",
                "οὐδὲν",
                "οὐκ",
                "οὐχ",
                "οὐχὶ",
                "οὓς",
                "οὔτε",
                "οὕτω",
                "οὕτως",
                "οὕτωσ",
                "οὖν",
                "οὗ",
                "οὗτος",
                "οὗτοσ",
                "παρ",
                "παρά",
                "παρα",
                "παρὰ",
                "περί",
                "περὶ",
                "ποια",
                "ποιεσ",
                "ποιο",
                "ποιοι",
                "ποιοσ",
                "ποιουσ",
                "ποιων",
                "ποτε",
                "που",
                "ποῦ",
                "προ",
                "προσ",
                "πρόσ",
                "πρὸ",
                "πρὸς",
                "πως",
                "πωσ",
                "σε",
                "στη",
                "στην",
                "στο",
                "στον",
                "σόσ",
                "σύ",
                "σύν",
                "σὸς",
                "σὺ",
                "σὺν",
                "τά",
                "τήν",
                "τί",
                "τίς",
                "τίσ",
                "τα",
                "ταῖς",
                "τε",
                "την",
                "τησ",
                "τι",
                "τινα",
                "τις",
                "τισ",
                "το",
                "τοί",
                "τοι",
                "τοιοῦτος",
                "τοιοῦτοσ",
                "τον",
                "τοτε",
                "του",
                "τούσ",
                "τοὺς",
                "τοῖς",
                "τοῦ",
                "των",
                "τό",
                "τόν",
                "τότε",
                "τὰ",
                "τὰς",
                "τὴν",
                "τὸ",
                "τὸν",
                "τῆς",
                "τῆσ",
                "τῇ",
                "τῶν",
                "τῷ",
                "ωσ",
                "ἀλλ'",
                "ἀλλά",
                "ἀλλὰ",
                "ἀλλ’",
                "ἀπ",
                "ἀπό",
                "ἀπὸ",
                "ἀφ",
                "ἂν",
                "ἃ",
                "ἄλλος",
                "ἄλλοσ",
                "ἄν",
                "ἄρα",
                "ἅμα",
                "ἐάν",
                "ἐγώ",
                "ἐγὼ",
                "ἐκ",
                "ἐμόσ",
                "ἐμὸς",
                "ἐν",
                "ἐξ",
                "ἐπί",
                "ἐπεὶ",
                "ἐπὶ",
                "ἐστι",
                "ἐφ",
                "ἐὰν",
                "ἑαυτοῦ",
                "ἔτι",
                "ἡ",
                "ἢ",
                "ἣ",
                "ἤ",
                "ἥ",
                "ἧς",
                "ἵνα",
                "ὁ",
                "ὃ",
                "ὃν",
                "ὃς",
                "ὅ",
                "ὅδε",
                "ὅθεν",
                "ὅπερ",
                "ὅς",
                "ὅσ",
                "ὅστις",
                "ὅστισ",
                "ὅτε",
                "ὅτι",
                "ὑμόσ",
                "ὑπ",
                "ὑπέρ",
                "ὑπό",
                "ὑπὲρ",
                "ὑπὸ",
                "ὡς",
                "ὡσ",
                "ὥς",
                "ὥστε",
                "ὦ",
                "ᾧ"
        ));
    }
}
