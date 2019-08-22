(ns stepsearch.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.data.zip.xml :refer (attr text xml->)]
            [clojure.xml :as xml]
            [clojure.zip :as zip])
  (:import (java.net URI)
           (java.io ByteArrayInputStream)
           (org.crosswire.jsword.book.sword BlockType
                                            SwordBook
                                            SwordBookMetaData
                                            ZVerseBackend)
           (org.xml.sax SAXParseException)))

(defn -main
  "This function is entirely exploratory. Don't use it."
  [& args]
  (let [f (io/file (io/resource "esv2016/mods.d/ESV2016_th.conf"))
        meta (SwordBookMetaData. f (.toURI (io/resource "esv2016")))
        blockType (BlockType/fromString (.getProperty meta SwordBookMetaData/KEY_BLOCK_TYPE))
        datasize 2
        zversebackend (ZVerseBackend. meta blockType datasize)
        book (SwordBook. meta zversebackend)
        keylist (.getGlobalKeyList book)]
    (doseq [k (take 5 keylist)]
      (let [text (str "<doc>" (.getRawText book k) "</doc>") ;; wrap because otherwise invalid XML :(
            xml (try
                  (xml/parse (ByteArrayInputStream. (.getBytes text "UTF-8")))
                  (catch SAXParseException e
                    "_malformed_"))
            zipped (zip/xml-zip xml)]
        (println text)
        (println (:content xml))
        (println (:content (first zipped)))))))
