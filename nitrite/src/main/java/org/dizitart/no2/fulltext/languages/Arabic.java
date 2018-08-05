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
 * Arabic stop words
 *
 * @since 2.1.0
 * @author Anindya Chatterjee
 */
public class Arabic implements Language {
    @Override
    public Set<String> stopWords() {
        return new HashSet<>(Arrays.asList(
                "،",
                "آض",
                "آمينَ",
                "آه",
                "آهاً",
                "آي",
                "أ",
                "أب",
                "أجل",
                "أجمع",
                "أخ",
                "أخذ",
                "أصبح",
                "أضحى",
                "أقبل",
                "أقل",
                "أكثر",
                "ألا",
                "أم",
                "أما",
                "أمامك",
                "أمامكَ",
                "أمسى",
                "أمّا",
                "أن",
                "أنا",
                "أنت",
                "أنتم",
                "أنتما",
                "أنتن",
                "أنتِ",
                "أنشأ",
                "أنّى",
                "أو",
                "أوشك",
                "أولئك",
                "أولئكم",
                "أولاء",
                "أولالك",
                "أوّهْ",
                "أي",
                "أيا",
                "أين",
                "أينما",
                "أيّ",
                "أَنَّ",
                "أََيُّ",
                "أُفٍّ",
                "إذ",
                "إذا",
                "إذاً",
                "إذما",
                "إذن",
                "إلى",
                "إليكم",
                "إليكما",
                "إليكنّ",
                "إليكَ",
                "إلَيْكَ",
                "إلّا",
                "إمّا",
                "إن",
                "إنّما",
                "إي",
                "إياك",
                "إياكم",
                "إياكما",
                "إياكن",
                "إيانا",
                "إياه",
                "إياها",
                "إياهم",
                "إياهما",
                "إياهن",
                "إياي",
                "إيهٍ",
                "إِنَّ",
                "ا",
                "ابتدأ",
                "اثر",
                "اجل",
                "احد",
                "اخرى",
                "اخلولق",
                "اذا",
                "اربعة",
                "ارتدّ",
                "استحال",
                "اطار",
                "اعادة",
                "اعلنت",
                "اف",
                "اكثر",
                "اكد",
                "الألاء",
                "الألى",
                "الا",
                "الاخيرة",
                "الان",
                "الاول",
                "الاولى",
                "التى",
                "التي",
                "الثاني",
                "الثانية",
                "الذاتي",
                "الذى",
                "الذي",
                "الذين",
                "السابق",
                "الف",
                "اللائي",
                "اللاتي",
                "اللتان",
                "اللتيا",
                "اللتين",
                "اللذان",
                "اللذين",
                "اللواتي",
                "الماضي",
                "المقبل",
                "الوقت",
                "الى",
                "اليوم",
                "اما",
                "امام",
                "امس",
                "ان",
                "انبرى",
                "انقلب",
                "انه",
                "انها",
                "او",
                "اول",
                "اي",
                "ايار",
                "ايام",
                "ايضا",
                "ب",
                "بات",
                "باسم",
                "بان",
                "بخٍ",
                "برس",
                "بسبب",
                "بسّ",
                "بشكل",
                "بضع",
                "بطآن",
                "بعد",
                "بعض",
                "بك",
                "بكم",
                "بكما",
                "بكن",
                "بل",
                "بلى",
                "بما",
                "بماذا",
                "بمن",
                "بن",
                "بنا",
                "به",
                "بها",
                "بي",
                "بيد",
                "بين",
                "بَسْ",
                "بَلْهَ",
                "بِئْسَ",
                "تانِ",
                "تانِك",
                "تبدّل",
                "تجاه",
                "تحوّل",
                "تلقاء",
                "تلك",
                "تلكم",
                "تلكما",
                "تم",
                "تينك",
                "تَيْنِ",
                "تِه",
                "تِي",
                "ثلاثة",
                "ثم",
                "ثمّ",
                "ثمّة",
                "ثُمَّ",
                "جعل",
                "جلل",
                "جميع",
                "جير",
                "حار",
                "حاشا",
                "حاليا",
                "حاي",
                "حتى",
                "حرى",
                "حسب",
                "حم",
                "حوالى",
                "حول",
                "حيث",
                "حيثما",
                "حين",
                "حيَّ",
                "حَبَّذَا",
                "حَتَّى",
                "حَذارِ",
                "خلا",
                "خلال",
                "دون",
                "دونك",
                "ذا",
                "ذات",
                "ذاك",
                "ذانك",
                "ذانِ",
                "ذلك",
                "ذلكم",
                "ذلكما",
                "ذلكن",
                "ذو",
                "ذوا",
                "ذواتا",
                "ذواتي",
                "ذيت",
                "ذينك",
                "ذَيْنِ",
                "ذِه",
                "ذِي",
                "راح",
                "رجع",
                "رويدك",
                "ريث",
                "رُبَّ",
                "زيارة",
                "سبحان",
                "سرعان",
                "سنة",
                "سنوات",
                "سوف",
                "سوى",
                "سَاءَ",
                "سَاءَمَا",
                "شبه",
                "شخصا",
                "شرع",
                "شَتَّانَ",
                "صار",
                "صباح",
                "صفر",
                "صهٍ",
                "صهْ",
                "ضد",
                "ضمن",
                "طاق",
                "طالما",
                "طفق",
                "طَق",
                "ظلّ",
                "عاد",
                "عام",
                "عاما",
                "عامة",
                "عدا",
                "عدة",
                "عدد",
                "عدم",
                "عسى",
                "عشر",
                "عشرة",
                "علق",
                "على",
                "عليك",
                "عليه",
                "عليها",
                "علًّ",
                "عن",
                "عند",
                "عندما",
                "عوض",
                "عين",
                "عَدَسْ",
                "عَمَّا",
                "غدا",
                "غير",
                "ـ",
                "ف",
                "فان",
                "فلان",
                "فو",
                "فى",
                "في",
                "فيم",
                "فيما",
                "فيه",
                "فيها",
                "قال",
                "قام",
                "قبل",
                "قد",
                "قطّ",
                "قلما",
                "قوة",
                "كأنّما",
                "كأين",
                "كأيّ",
                "كأيّن",
                "كاد",
                "كان",
                "كانت",
                "كذا",
                "كذلك",
                "كرب",
                "كل",
                "كلا",
                "كلاهما",
                "كلتا",
                "كلم",
                "كليكما",
                "كليهما",
                "كلّما",
                "كلَّا",
                "كم",
                "كما",
                "كي",
                "كيت",
                "كيف",
                "كيفما",
                "كَأَنَّ",
                "كِخ",
                "لئن",
                "لا",
                "لات",
                "لاسيما",
                "لدن",
                "لدى",
                "لعمر",
                "لقاء",
                "لك",
                "لكم",
                "لكما",
                "لكن",
                "لكنَّما",
                "لكي",
                "لكيلا",
                "للامم",
                "لم",
                "لما",
                "لمّا",
                "لن",
                "لنا",
                "له",
                "لها",
                "لو",
                "لوكالة",
                "لولا",
                "لوما",
                "لي",
                "لَسْتَ",
                "لَسْتُ",
                "لَسْتُم",
                "لَسْتُمَا",
                "لَسْتُنَّ",
                "لَسْتِ",
                "لَسْنَ",
                "لَعَلَّ",
                "لَكِنَّ",
                "لَيْتَ",
                "لَيْسَ",
                "لَيْسَا",
                "لَيْسَتَا",
                "لَيْسَتْ",
                "لَيْسُوا",
                "لَِسْنَا",
                "ما",
                "ماانفك",
                "مابرح",
                "مادام",
                "ماذا",
                "مازال",
                "مافتئ",
                "مايو",
                "متى",
                "مثل",
                "مذ",
                "مساء",
                "مع",
                "معاذ",
                "مقابل",
                "مكانكم",
                "مكانكما",
                "مكانكنّ",
                "مكانَك",
                "مليار",
                "مليون",
                "مما",
                "ممن",
                "من",
                "منذ",
                "منها",
                "مه",
                "مهما",
                "مَنْ",
                "مِن",
                "نحن",
                "نحو",
                "نعم",
                "نفس",
                "نفسه",
                "نهاية",
                "نَخْ",
                "نِعِمّا",
                "نِعْمَ",
                "ها",
                "هاؤم",
                "هاكَ",
                "هاهنا",
                "هبّ",
                "هذا",
                "هذه",
                "هكذا",
                "هل",
                "هلمَّ",
                "هلّا",
                "هم",
                "هما",
                "هن",
                "هنا",
                "هناك",
                "هنالك",
                "هو",
                "هي",
                "هيا",
                "هيت",
                "هيّا",
                "هَؤلاء",
                "هَاتانِ",
                "هَاتَيْنِ",
                "هَاتِه",
                "هَاتِي",
                "هَجْ",
                "هَذا",
                "هَذانِ",
                "هَذَيْنِ",
                "هَذِه",
                "هَذِي",
                "هَيْهَاتَ",
                "و",
                "و6",
                "وا",
                "واحد",
                "واضاف",
                "واضافت",
                "واكد",
                "وان",
                "واهاً",
                "واوضح",
                "وراءَك",
                "وفي",
                "وقال",
                "وقالت",
                "وقد",
                "وقف",
                "وكان",
                "وكانت",
                "ولا",
                "ولم",
                "ومن",
                "وهو",
                "وهي",
                "ويكأنّ",
                "وَيْ",
                "وُشْكَانََ",
                "يكون",
                "يمكن",
                "يوم",
                "ّأيّان"
        ));
    }
}
