package ExceptionHandlingQuestions;


// Program that incorrectly uses Exception

//public class Q10_ExceptionControlMechanism {
//
//    public static void main(String[] args) {
//        int[] numbers = {1, 2, 3, 4, 5};
//        try {
//            for (int number : numbers) {
//                if (number == 3) {
//                    throw new Exception("Breaking out of loop");
//                }
//                System.out.println("Number: " + number);
//            }
//        } catch (Exception e) {
//            System.out.println("Caught exception: " + e.getMessage());
//        }
//    }
//}


// Program that correctly uses Exception
public class Q10_ExceptionControlMechanism {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        for (int number : numbers) {
            if (number == 3) {
                System.out.println("Breaking out of loop");
                break;
            }
            System.out.println("Number: " + number);
        }
    }
}
