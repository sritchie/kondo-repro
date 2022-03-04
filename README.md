# Kondo Repro

The bug seems to be that

```
#_{:clj-kondo/ignore [:sicmutils.pattern/number]}
```

Does not work.

Version:

```
$ clj-kondo --version
clj-kondo v2022.03.04
```

`src/pattern/rule.cljc` contains:

```clj
(ns pattern.rule)

(defmacro cake [x sym]
  `(def ~sym ~x))

#_{:clj-kondo/ignore [:sicmutils.pattern/number]}
(cake "string" binder)
```

With `.clj-kondo/config.edn`:

```clj
{:config-paths ["../resources/clj-kondo.exports/sicmutils/sicmutils"]
 ;; this override DOES work. I had been nesting this kv pair inside some other
 ;; map, whoops....
 :linters
 {:sicmutils.pattern/number {:level :warning}}}
```

Try

```
clj-kondo --lint src/pattern/rule.cljc
```

And note the result:

```sh
$ clj-kondo --lint src/pattern/rule.cljc
src/pattern/rule.cljc:7:7: error: binding value must be a number!
```
