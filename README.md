# Full Text Search On Local Files With Inverted Index

![banner](https://github.com/shubham0204/full-text-search/assets/41076823/018f35f2-75e3-4589-938c-46e33fe259d9)

## Demo

![demo_gif](https://github.com/shubham0204/full-text-search/assets/41076823/9b24636a-a340-4e69-8c37-3760534a3909)

## Features

* Text extraction from PDFs, Microsoft Word DOCX and text-based formats
* Disk-persistence of inverted index
* Validation of inverted index
* Command-line utility

## Setup

Make sure Java is installed on your system, with `JAVA_HOME` pointing to a JDK installation. 
You may clone the project from the GitHub repository, and build it with `gradlew` present in the root of the 
repository,

```
$> git clone https://github.com/shubham0204/full-text-search
$> cd full-text-search
$> ./gradlew build
```

To execute tests,

```
$> ./gradlew test
```

To build the fat/uber JAR,

```
$> ./gradlew shadowJar
```

## Usage

### Index

```
$> java -jar fulltextsearch.jar index build [dir]
$> java -jar fulltextsearch.jar index info [dir]
$> java -jar fulltextsearch.jar index rm [dir]
```

Use `fulltextsearch index --help` for description of each command.

### Query

```
$> fulltextsearch query [dir]
```

## Dependencies

* [Apache PdfBox](https://pdfbox.apache.org/)
* [Apache POI](https://poi.apache.org/)
* [picocli](https://picocli.info/)
* [shadow](https://github.com/johnrengelman/shadow)

## Useful Resources

* [Wikipedia - Full Text Search](https://en.wikipedia.org/wiki/Full-text_search)
* [Let's build a Full-Text Search engine](https://artem.krylysov.com/blog/2020/07/28/lets-build-a-full-text-search-engine/)
* [Building a full-text search engine in 150 lines of Python code](https://bart.degoe.de/building-a-full-text-search-engine-150-lines-of-code/)
* [Building a Full Text Search Engine](https://blog.quastor.org/p/building-full-text-search-engine)
* [How to Implement Inverted Index Data Structure in Java](https://taruntelang.medium.com/how-to-implement-inverted-index-data-structure-in-java-14067093acd4)
