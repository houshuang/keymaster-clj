  ; Provides a bridge to the jkeymaster library
  ;  First acquire a provider, and then register a keyboard shortcut

  ;  Example code:
  ;  (let [keyprovider (keymaster.core/provider)]
  ;    (keyprovider "control shift 1" #(println)))

(ns keymaster.core
  (:gen-class)
  (:use (com.tulskiy.keymaster.common)))

(defn- conv-keystroke [x]
  "Takes keystroke in the form \"control shift 1\" and returns a Keystroke class"
  (javax.swing.KeyStroke/getKeyStroke x))

(defn- conv-listener [f]
  "Takes a function with one argument, which will get passed the keycode, and creates a listener
   Todo: How to accept a function with or without a parameter to accept hotKey?"
   (proxy [com.tulskiy.keymaster.common.HotKeyListener] [] (onHotKey [hotKey] (f))))

(declare provider)

(defn register
  [provider shortcut listener]
  "Registers a shortcut on provider, which will trigger listener (with one argument)"
  (let [k (conv-keystroke shortcut)
        l (conv-listener listener)]
    (.register provider k l)))

(defn provider []
  "Gets and initiates a keymaster provider, which must be passed to register to register shortcuts"
  (let [provider (com.tulskiy.keymaster.common.Provider/getCurrentProvider true)]
    (.init provider)
    (partial register provider)))