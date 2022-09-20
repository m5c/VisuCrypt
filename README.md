# Visu Crypt

Visual XOR based encryption with random one time pads.

![version](https://img.shields.io/badge/version-1.1-brightgreen)
![coverage](https://img.shields.io/badge/coverage-100%25-brightgreen)
![building](https://img.shields.io/badge/build-passing-brightgreen)

## About

[Visual Cryptography](https://en.wikipedia.org/wiki/Visual_cryptography) is an approach to split information over image shares, where:
 * Every share by itself leaves no information about the content.
 * An image superposition (images printed on foil, stacked arrangement) reveals the original secret.  
This repository hosts an open source implementation.

## Documentation

See [JavaDoc on GitHub pages](https://kartoffelquadrat.github.io/VisuCrypt/).

## Usage

 * Compile the sources: ```mvn clean package```
 * Run the program: ```java -jar target/VisuCrypt.jar path/to/source/image.jpg``` or  
 ```mvn clean package exec:java -Dexec.args=path/to/source/image.jpg```
 * Print shares, generated to ```/tmp``` directory.
 * Place printed shares one on top of the other, hold against a light source.

## License

[MIT](LICENSE)

## Author

Maximilian Schiedermeier, 2018 - 2022  
At the time employed at [INSA Lyon, LIRIS Labs](https://liris.cnrs.fr/page-membre/maximilian-schiedermeier).
