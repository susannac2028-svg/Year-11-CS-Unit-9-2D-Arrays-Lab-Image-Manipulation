package code;
import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /**
     * CHALLENGE 0: Display Image
     * Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage image = new APImage("cyberpunk2077.jpg");
        image.draw();
        grayScale("cyberpunk2077.jpg");
        blackAndWhite("cyberpunk2077.jpg");
        edgeDetection("cyberpunk2077.jpg", 20);
        reflectImage("cyberpunk2077.jpg");
        rotateImage("cyberpunk2077.jpg");

    }

    /**
     * CHALLENGE ONE: Grayscale
     * <p>
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     * <p>
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value.
     */
    public static void grayScale(String pathOfFile) {
        APImage color = new APImage(pathOfFile);
        APImage newImage = color.clone();

        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                Pixel a = newImage.getPixel(i, j);
                int b = a.getRed();
                int c = a.getGreen();
                int d = a.getBlue();
                int e = ((b + c + d) / 3);
                a.setRed(e);
                a.setBlue(e);
                a.setGreen(e);
            }
        }
        newImage.draw();
    }

    /**
     * A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     *
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int a = pixel.getRed();
        int b = pixel.getGreen();
        int c = pixel.getBlue();
        return ((a + b + c) / 3);
    }

    /**
     * CHALLENGE TWO: Black and White
     * <p>
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     * <p>
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white
     */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Pixel a = image.getPixel(i, j);
                int b = a.getRed();
                int c = a.getGreen();
                int d = a.getBlue();
                int avg = ((b + c + d) / 3);
                if (avg >= 128) {
                    a.setRed(225);
                    a.setGreen(225);
                    a.setBlue(225);
                } else {
                    a.setRed(0);
                    a.setGreen(0);
                    a.setBlue(0);
                }
            }
        }
        image.draw();

    }

    /**
     * CHALLENGE Three: Edge Detection
     * <p>
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     * <p>
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     * <p>
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *
     */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage image = new APImage(pathToFile);
        APImage newImage = image.clone();
        for (int i = 1; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight() - 1; j++) {
                Pixel a = newImage.getPixel(i, j);
                Pixel left = image.getPixel(i - 1, j);
                Pixel down = image.getPixel(i, j + 1);
                int b = a.getRed();
                int c = a.getGreen();
                int d = a.getBlue();
                int aAverage = ((b + c + d) / 3);

                int bLeft = left.getRed();
                int cLeft = left.getGreen();
                int dLeft = left.getBlue();
                int leftAverage = ((bLeft + cLeft + dLeft) / 3);

                int bDown = down.getRed();
                int cDown = down.getGreen();
                int dDown = down.getBlue();
                int downAverage = ((bDown + cDown + dDown) / 3);

                //test
                int diffOne = aAverage - leftAverage;
                int diffTwo = aAverage - downAverage;
                diffOne = Math.abs(diffOne);
                diffTwo = Math.abs(diffTwo);

                if (diffOne < 0) {
                    diffOne = -diffOne;
                }
                if (diffTwo < 0) {
                    diffTwo = -diffTwo;
                }
                if (diffOne > threshold || diffTwo > threshold) {
                    a.setRed(0);
                    a.setGreen(0);
                    a.setBlue(0);
                } else {
                    a.setRed(255);
                    a.setGreen(255);
                    a.setBlue(255);
                }

            }

        }
        newImage.draw();
    }

    /**
     * CHALLENGE Four: Reflect Image
     * <p>
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        APImage newImage = image.clone();

        for (int i = 0; i < newImage.getHeight(); i++) {
            for (int j = 0; j < newImage.getWidth() / 2; j++) {
                Pixel b = newImage.getPixel(j, i);
                Pixel a = newImage.getPixel(newImage.getWidth() - 1 - j, i);
                newImage.setPixel(j, i, a);
                newImage.setPixel(newImage.getWidth() - 1 - j, i, b);
            }
        }
        newImage.draw();
    }

    /**
     * CHALLENGE Five: Rotate Image
     * <p>
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *
     */
    public static void rotateImage(String pathToFile) {
        APImage image = new APImage(pathToFile);

        int ogWidth = image.getWidth();
        int ogHeight = image.getHeight();

        APImage newImage = new APImage(ogHeight, ogWidth);

        for (int i = 0; i < ogWidth; i++) {
            for (int j = 0; j < ogHeight; j++) {
                Pixel a = image.getPixel(i, j);
                int newX = ogHeight - 1 - j;
                int newY = i;
                newImage.setPixel(newX, newY, a);
            }
        }
        newImage.draw();
    }
}





