package eu.kartoffelquadrat.visucrypt;

public class Launcher {

    // Some params:
    // input image 256x256 -> output 512x512 -> conv png->pdf, exceeds a4, exceeds a3
    // input image 128x128 -> output 256x256 -> conf png->pdf, fits a4, fits a3
    //          half more than second conf would still fit a3, exceed a4. -> do this. Better: with double sized blocks!
    public static void main(String[] args) {

        // load image as binary array
        boolean[][] inputImageBinary = BinaryImageLoader.loadImage(args[0]);
        System.out.println("Image imported. Detected size: "+inputImageBinary.length);

        // create a random one time pad of same size, also as boolean 2D array
        boolean[][] oneTimePad = OneTimePadGenerator.generatePad(inputImageBinary.length, inputImageBinary[0].length);
        System.out.println("Pad generated.");

        // create XOR-ed outcome of input_image and one_time_pad
        // Idea: white pixels in original image require identical booleans in both shares. Black pixels require complementary pixels.

        boolean[][] secondShare = BoolArrayXorer.weave(inputImageBinary, oneTimePad);
        System.out.println("Second share created.");

        // convert the compact boolean-shares to double sized visual shares
        boolean[][] visualShare1 = Upscaler.upscale(oneTimePad);
        boolean[][] visualShare2 = Upscaler.upscale(secondShare);

        // Finally export both shares as pngs back to the filesystem, so they can be printed
        VisualShareExporter.exportVisualShare(visualShare1, "/tmp/share1.png");
        VisualShareExporter.exportVisualShare(visualShare2, "/tmp/share2.png");
    }
}
