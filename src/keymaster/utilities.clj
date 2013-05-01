(ns keymaster.utilities)

(defn arg-count [function]
  "Counts the number of arguments the given function accepts"
  (let [method (first (.getDeclaredMethods (class function)))
        parameters (.getParameterTypes method)]
    (alength parameters)))

(defn call-with-correct-args [function & args]
  "Call the given function on all given args that it can accept"
  (let [amount-accepted (arg-count function)
        accepted-args (take amount-accepted args)]
    (if-not (= 0 amount-accepted)
      (apply function accepted-args)
    ;else
      (function))))
