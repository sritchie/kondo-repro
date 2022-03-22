# Kondo Repro

The problem is that anything defined in `sicmutils.env` with `import-def` is not recognized by the linter, but only in a project using `sicmutils` as a dependency, not the actual codebase.

Version:

```
$ clj-kondo --version
clj-kondo v2022.03.04
```

Try

```
$ clj-kondo --lint src/example.clj
src/example.clj:7:36: warning: Unresolved var: principal-value
```

That function is defined using `import-def`:
https://github.com/sicmutils/sicmutils/blob/main/src/sicmutils/env.cljc#L195

Which DOES have a hook: https://github.com/sicmutils/sicmutils/blob/main/resources/clj-kondo.exports/sicmutils/sicmutils/hooks/sicmutils/util/def.clj#L4-L14

that is definitely getting called when I lint dependencies:

```
clj-kondo --lint "$(clojure -Spath)"
```

I put a `prn` statement before `{:node new-node}` in the imported hook and it
definitely triggers.
