(ns howistartclojure.generator-test
  (:require [clojure.test :refer :all]
            [howistartclojure.generator :refer :all]))

(deftest markov-test
  (let [example "and the golden grouse and the pobble who"
        word-chain (make-word-chain example)]
    (testing "With a simple example"
      (is (= (word-chain ["and" "the"]) #{"golden" "pobble"}))

      (testing "Should generate a proper sentence (and terminate)."
        (generate-sentence word-chain)))))

(deftest word-chain->word-list-test
  (testing "Empty word chain arg gives an empty vec."
    (let [empty-word-chain (make-word-chain "")]
      (is (= (word-chain->word-list empty-word-chain)
             []))))
  (testing "all words should be found in generated word list."
    (let [sample-sentence "hi there yo"
          sample-word-chain (make-word-chain sample-sentence)
          word-list (word-chain->word-list sample-word-chain)]
      (doall (for [word (clojure.string/split sample-sentence #" ")]
               (is (contains? word-list word)))))))
