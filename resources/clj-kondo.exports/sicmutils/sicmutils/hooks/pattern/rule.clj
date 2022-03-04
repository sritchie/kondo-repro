(ns hooks.pattern.rule
  (:require [clj-kondo.hooks-api :as api]))

(defn cake [{:keys [node]}]
  (let [[_ v sym] (:children node)]
    (when-not (number? (:value v))
      (let [{:keys [row col]} (meta v)]
        (api/reg-finding!
         {:message
          (str "binding value must be a number!")
          :type :sicmutils.pattern/number
          :row row
          :col col})))
    {:node (api/list-node
            [(api/token-node 'def) sym v])}))
