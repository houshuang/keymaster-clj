; Just a simple example - registers two shortcuts, one prints Hello, the other
; de-registers shortcut keys. Run with lein run -m keymaster.example/main

(ns keymaster.example
  (:gen-class)
  (:require [keymaster.core :as km]))

(defn- main []
  (let [prov (km/make-provider)]
    (println prov)
    (km/register prov "control shift 1" #(km/stop prov))
    (km/register prov "control shift 2" #(println "Hello"))
    (loop []  (Thread/sleep 1000)  (println "hello") (recur))))