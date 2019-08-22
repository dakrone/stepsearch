(ns stepsearch.core
  (:gen-class)
  (:require [clojure.java.io :as io])
  (:import (java.net URI)
           (org.crosswire.jsword.book.sword BlockType
                                            SwordBook
                                            SwordBookMetaData
                                            ZVerseBackend)))

(defn -main
  "I don't do a whole lot."
  [& args]
  (let [f (io/file (io/resource "esv2016/mods.d/ESV2016_th.conf"))
        meta (SwordBookMetaData. f (.toURI (io/resource "esv2016")))
        ;; BlockType blockType = BlockType.fromString(sbmd.getProperty(SwordBookMetaData.KEY_BLOCK_TYPE))
        blockType (BlockType/fromString (.getProperty meta SwordBookMetaData/KEY_BLOCK_TYPE))
        datasize 2
        zversebackend (ZVerseBackend. meta blockType datasize)
        book (SwordBook. meta zversebackend)
        keylist (.getGlobalKeyList book)]
    (doseq [k keylist]
      (println (.getRawText book k)))))
