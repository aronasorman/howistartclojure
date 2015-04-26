(ns howistartclojure.generator
  (:refer-clojure  :exclude [fn for defn])
  (:require [clojure.core.typed :as t
             :refer [fn Vec Coll Any Num Set Map]]))

(t/defalias WordChain (Map (Vec String) (Set String)))
(t/defalias Chain (t/HVec [String String String]))

;; couldn't resolve the reason for the internal type errors. Still
;; works in practice though.
(t/defn ^:no-check make-word-chain [text :- String] :- WordChain
  (let [word-groupings (partition-all 3 1 (clojure.string/split text #" "))]
    (reduce (fn [result :- WordChain, grouping :- Chain] :- WordChain
              (merge-with clojure.set/union result
                          (let [[a b c] grouping]
                            {[a b] (if c #{c} #{})})))
            {}
            word-groupings)))

(t/defn word-chain->word-list [word-chain :- WordChain] :- (Vec String)
  )

(t/defn generate-sentence [chain :- WordChain] :- String)
