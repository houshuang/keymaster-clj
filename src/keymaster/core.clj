  ; Provides a bridge to the jkeymaster library
  ;  First acquire a provider, and then register a keyboard shortcut

  ;  Example code:
  ;  (let [provider (keymaster.core/make-provider)]
  ;    (provider "control shift 1" #(println)))

(ns keymaster.core
  (:gen-class)
  (:use (com.tulskiy.keymaster.common)
        [keymaster.utilites :only [arg-count]]))

(defn- conv-keystroke [x]
  "Takes keystroke in the form \"control shift 1\" and returns a Keystroke class"
  (javax.swing.KeyStroke/getKeyStroke x))

(defn- conv-listener [function]
  "Takes a function with one argument, which will get passed the keycode, and creates a listener
   Todo: How to accept a function with or without a parameter to accept hotKey?"
   (proxy [com.tulskiy.keymaster.common.HotKeyListener] []
    (onHotKey [hotKey]
      (if (= 1 (arg-count function))
        (function hotKey)
      ;else
        (function)))))

(defn register
  [provider shortcut listener]
  "Registers a shortcut on provider, which will trigger listener"
  (let [k (conv-keystroke shortcut)
        l (conv-listener listener)]
    (.register provider k l)))

(defn make-provider []
  "Gets and initiates a keymaster provider, returns partial function which can be used to register shortcuts"
  (let [provider (com.tulskiy.keymaster.common.Provider/getCurrentProvider true)]
    (.init provider)
    (partial register provider)))