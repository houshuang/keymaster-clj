  ; Provides a bridge to the jkeymaster library
  ;  First acquire a provider, and then register a keyboard shortcut

  ;  Example code:
  ;  (let [provider (keymaster.core/make-provider)]
  ;    (keymaster.core/register provider "control shift 1" #(println)))
  ;  When you are done, use (keymaster.core/stop provider) to reset keyboard shortcuts.
  ;  Use keyboard.core/make-provider to create a new provider to register keyboard shortcuts after this.

(ns keymaster.core
  (:gen-class)
  (:use (com.tulskiy.keymaster.common)
        [keymaster.utilities :only [call-with-correct-args]]))

(defn- conv-keystroke [keystroke-string]
  "Takes keystroke in the form \"control shift 1\" and returns a Keystroke class"
  (javax.swing.KeyStroke/getKeyStroke keystroke-string))

(defn- conv-listener [function]
  "Takes a function with one argument, which will get passed the keycode, and creates a listener"
  (proxy [com.tulskiy.keymaster.common.HotKeyListener] []
    (onHotKey [hotKey] (call-with-correct-args function hotKey))))

(defn register [provider shortcut listener]
  "Registers a shortcut on provider, which will trigger listener"
  (let [keystroke          (conv-keystroke shortcut)
        keystroke-listener (conv-listener listener)]
    (.register provider keystroke keystroke-listener)))

(defn stop [provider]
  "Resets keyboard shortcuts and stops a provider. Call make-provider again to register new shortcuts"
  (-> provider
      .reset
      .stop))

(defn make-provider []
  "Gets and initiates a keymaster provider, returns partial function which can be used to register shortcuts"
  (let [provider (com.tulskiy.keymaster.common.Provider/getCurrentProvider true)]
    (.init provider)
    provider))
