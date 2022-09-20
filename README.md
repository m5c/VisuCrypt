# Visu Crypt

Visual XOR based encryption with random one time pads.

## About

[Visual Cryptography](https://en.wikipedia.org/wiki/Visual_cryptography) is an approach to split information over image shares, where:
 * Every share by itself leaves no information about the content.
 * An image superposition (images printed on foil, stacked arrangement) reveals the original secret.  
This repository hosts an open source implementation.

## Usage

 * Compile the sources: ```mvn clean package```
 * Run the program: ```java -jar target/VisuCrypt.jar path/to/source/image.jpg```
 * Print shares, generated to ```/tmp``` directory.
 * Place printed shares one on top of the other, hold agains a light source.

## Ideas for improvement

 * Edge detection with sobel filter
 * Dither mode for grayscale approximations

 * Black / white in stacked images is inverted.

## License

[MIT](LICENSE)

## Author

Maximilian Schiedermeier, 2018  
At the time employed at [INSA Lyon, LIRIS Labs](https://liris.cnrs.fr/page-membre/maximilian-schiedermeier).
