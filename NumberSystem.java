public class NumberSystem{

    //Types supported for Number system
    enum Type {
        INTERNATIONAL,
        INDIAN
    }
    final String ZERO = "zero";
    final String ONE = "one";
    final String PLULAR_SUFFIX = "s";
    final String SINGULAR_SUFFIX = " ";
    final String SPACE = " ";
    final String ONES[] ={
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
    };
    final String TWOS[] = {
        "eleven",
        "twelve",
        "thirteen",
        "fourteen",
        "fifteen",
        "sixteen",
        "seventeen",
        "eighteen",
        "nineteen"
    };
    final String TENS[] = {
        "ten",
        "twenty",
        "thirty",
        "fourty",
        "fifty",
        "sixty",
        "seventy",
        "eighty",
        "ninety",
    };

    final String
            HUNDRED = "hundred",
            THOUSAND = "thousand",
            LAKH = "lakh",
            CRORE = "crore",
            ARAB = "arab";

    public String convert(Integer number) throws Exception {
        String var0 = Integer.toString(number);
        var0 = var0.replaceFirst("^0+(?!$)", "");
        if("" != var0 || null != var0) {
            if(var0.length() > 12) {
                throw new Exception("Digit's length exceed the threshold 11");
            }
            return getStringValue(var0);
        }
        return null;
    }
    private String getStringValue(String number) {
        String phrase = "";
        //if length is one
        if(number.length() == 1) {
            phrase = oneDigit(number);
        }
        //if length is two
        else if(number.length() == 2) {
            phrase = twoDigit(number);
        }
        //if length is 3
        else if (number.length() == 3) {
            phrase = threeDigit(number);
        }
        //if length is 4 or 5
        else if (number.length() == 4 || number.length() == 5) {
            phrase = fourOrFiveDigit(number);
        }
        //if length is 6 or 7
        else if (number.length() == 6 || number.length() == 7) {
            phrase = sixOrSevenDigit(number);
        }
        //if length is 4 or 5
        else if (number.length() == 8 || number.length() == 9) {
            phrase = eigthOrNineDigit(number);
        }
        //if length is 4 or 5
        else if (number.length() == 10 || number.length() == 11) {
            phrase = tenOrElevenDigit(number);
        }
        return phrase;
    }
    //when number is one digit
    private String oneDigit(String var1) {
        Integer index = Integer.parseInt(var1);
        if(index == 0) {
            return ZERO;
        }else {
            return ONES[index-1];
        }
    }

    //when number is two digit
    private String twoDigit(String var1) {
        String oneSubString = var1.substring(0,1);
        String twoSubString = var1.substring(1,var1.length());
        String one = oneDigit(oneSubString);
        String two = oneDigit(twoSubString);

        //check both the digit are zero
        if(ZERO.equals(one) && ZERO.equals(two)) {
            return ZERO;
        }

        //check if one is zero and two is not zero
        else if(ZERO.equals(one) && !ZERO.equals(two)) {
            return two;
        }

        //check if one is none zero and two is zero
        else if(!ZERO.equals(one) && ZERO.equals(two)) {
            Integer index = Integer.parseInt(oneSubString);
            return TENS[index-1];
        }
        // check if both the digits are none zero
        else {
            Integer index1 = Integer.parseInt(oneSubString);
            Integer index = Integer.parseInt(twoSubString);
            if(index1 == 1) {
                return TWOS[index-1];
            }else {
                return TENS[index1-1]+SPACE+ONES[index-1];
            }
        }
    }

    //when number is 3 digit
    private String threeDigit(String var1) {
        String oneSubString = var1.substring(0,1);
        String twoSubString = var1.substring(1,var1.length());

        String one = oneDigit(oneSubString);
        String two = twoDigit(twoSubString);

        return getPhrases(one,two,oneSubString,HUNDRED);
    }

    //when number is 4 or 5 digits
    private String fourOrFiveDigit(String var1) {
        String oneSubString = var1.substring(0,1);
        String one = "";
        String two = "";
        if(var1.length() == 4) {
            one = oneDigit(oneSubString);
            two = threeDigit(var1.substring(1,var1.length()));
        }else {
            one = twoDigit(var1.substring(0,2));
            two = threeDigit(var1.substring(2,var1.length()));
        }

        return getPhrases(one,two,oneSubString,THOUSAND);
    }

    private String sixOrSevenDigit(String var1) {
        String oneSubString = var1.substring(0,1);
        String one = "";
        String two = "";
        if(var1.length() == 6) {
            one = oneDigit(oneSubString);
            two = fourOrFiveDigit(var1.substring(1,var1.length()));
        }else {
            one = twoDigit(var1.substring(0,2));
            two = fourOrFiveDigit(var1.substring(2,var1.length()));
        }
        return getPhrases(one,two,oneSubString,LAKH);
    }
    private String eigthOrNineDigit(String var1) {
        String oneSubString = var1.substring(0,1);
        String one = "";
        String two = "";
        if(var1.length() == 8) {
            one = oneDigit(oneSubString);
            two = sixOrSevenDigit(var1.substring(1,var1.length()));
        }else {
            one = twoDigit(var1.substring(0,2));
            two = sixOrSevenDigit(var1.substring(2,var1.length()));
        }
        return getPhrases(one,two,oneSubString,CRORE);
    }
    private String tenOrElevenDigit(String var1) {
        String oneSubString = var1.substring(0,1);
        String one = "";
        String two = "";
        if(var1.length() == 10) {
            one = oneDigit(oneSubString);
            two = eigthOrNineDigit(var1.substring(1,var1.length()));
        }else {
            one = twoDigit(var1.substring(0,2));
            two = eigthOrNineDigit(var1.substring(2,var1.length()));
        }
        return getPhrases(one,two,oneSubString,ARAB);
    }
    private String getPhrases(String one, String two,String oneSubString, String PLACEHOLDER) {
        //check both the digit are zero
        if(ZERO.equals(one) && ZERO.equals(two)) {
            return ZERO;
        }

        //check if one is zero and two is not zero
        else if(ZERO.equals(one) && !ZERO.equals(two)) {
            return two;
        }

        //check if one is none zero and two is zero
        else if(!ZERO.equals(one) && ZERO.equals(two)) {
            Integer index = Integer.parseInt(oneSubString);
            return ONES[index-1] + SPACE + PLACEHOLDER  + (TENS[index-1] == ONE?PLULAR_SUFFIX:SINGULAR_SUFFIX);
        }
        // check if both the digits are none zero
        else {
            return one + SPACE + PLACEHOLDER + (one == ONE?PLULAR_SUFFIX:SINGULAR_SUFFIX)+ SPACE + two;
        }
    }

    //main function
    public static void main(String... args) throws Exception{
        NumberSystem numberSystem = new NumberSystem();
        String phrases = numberSystem.convert(403400);
        System.out.println(phrases);
    }
}