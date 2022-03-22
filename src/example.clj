(ns example
  ;; If I `:refer :all` I see an unresolved symbol on `principal-value`.
  #_(:require [sicmutils.env :refer :all])

  ;; If I activate this instead, this line gives an `unresolved var` warning,
  ;; but then `principal-value` below is fine.
  (:require [sicmutils.env :refer [principal-value]]))

;; Neither of these behaviors happen inside the `sicmutils` codebase with a
;; similar example, so it must be a problem with dependency linting.
(principal-value 10)
