# Full-Text-Search From Document Using Inverted Index

## Usage

### Index

```
$> fulltextsearch index build [dir]
$> fulltextsearch index info [dir]
$> fulltextsearch index rm [dir]
```

Use `fulltextsearch index --help` for description of each command.

### Search

```
$> fulltextsearch search [dir]
```

## Features

* Text extraction from PDFs, Microsoft Word DOCX and text-based formats
* Disk-persistence of inverted index
* Validation of inverted index
* Command-line utility

### Dependencies

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
