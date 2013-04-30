# Keymaster-clj

Keymaster-clj is a global shortcut library for Clojure. It functions as a tiny wrapper around [jkeymaster](https://github.com/tulskiy/jkeymaster), which is its only dependency.

## Thanks
@raek ([Rasmus Svensson](http://raek.se/)) helped me out a ton in the #clojure IRC channel to get this working, I really appreciate it. 

## Installation

For now, you have to grab it from GitHub. I'll try to learn how to put on clojars.

## Usage

```clojure
(require 'keymaster.core)

(def provider keymaster.core/make-provider)
(provider "control shift 1" #(println "You pressed 1!"))
```

You first acquire a provider with keymaster.core/provider, which initializes the library and returns a function which you can use to register keyboard shortcuts. You then call that function with the keyboard shortcut as a string, and the function callback.

## Multiplatform

The Java library jkeymaster is supposed to work on OSX, Linux and Windows. I've only tested this Clojure wrapper on OSX.

## Feedback
This is some of the first Clojure code I have written, so any feedback is appreciated, including making the code more "idiomatic", feedback on the design of the API etc. (Initially, I returned the provider as a var, and had users call a separate keymaster.core/register with the provider as an argument -- now I use a partial, which lets me only expose one function to users. Not sure which is better.)


## License

Copyright © 2013 Stian Haklev

Distributed under the Eclipse Public License, the same as Clojure.
