(ns pattern.rule)

(defmacro cake [x sym]
  `(def ~sym ~x))

#_{:clj-kondo/ignore [:sicmutils.pattern/number]}
(cake "string" binder)
