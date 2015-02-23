(ns introspect-tests.core)

(defn c
  []
  (+ 1 2))

(defn anonymous-fn
  []
  (fn [a b] (str "anon" a b)))

(defn sqr
  [a]
  (* a a))

(defn sum
  [a b]
  (+ b (sqr a)))

(defn simple-sum
  [a b]
  (+ a b))

(defn some-crazy-function
  [a b]
  (str "anon" a b))

(defn -main
  [& args]
  (compile 'introspect-tests.core) 
  (println (c))
  (println (sqr 2))
  (println (sum 2 1))
  (println (simple-sum 1 2))
  (println (some-crazy-function 1 2))
  (println (some-crazy-function "asd" "bsd"))
  )
