# Keymaster-clj

Keymaster-clj is a global shortcut library for Clojure. It functions as a tiny wrapper around [jkeymaster](https://github.com/tulskiy/jkeymaster), which is its only dependency.

## Thanks
@raek ([Rasmus Svensson](http://raek.se/)) helped me out a ton in the #clojure IRC channel to get this working, I really appreciate it.

## Installation

For now, you have to grab it from GitHub. I'll try to learn how to put on clojars.

## Usage

```clojure
(let [provider (keymaster.core/make-provider)]
  (keymaster.core/register provider "control shift 1" #(println %)))
```

You first acquire a provider with `keymaster.core/provider`, which initializes the library. You then call `keymaster.core/register` with the provider, the keyboard shortcut as a string, and the function callback. Once you're done, you call `keymaster.core/stop` on the provider to deregister the keyboard shortcuts, and cancel the provider. Note that this causes the REPL to hang, but seems to work fine in running apps. See `src/example.clj` for a functional example (run with `lein run -m keymaster.example/main`).

## Multiplatform

The Java library jkeymaster is supposed to work on OSX, Linux and Windows. I've only tested this Clojure wrapper on OSX, and I believe Michael Marsh has tested it on Ubuntu.

## Contributors

- Stian Håklev
- Michael Marsh

## License

Copyright © 2013 Stian Håklev <shaklev@gmail.com>

Distributed under the Eclipse Public License, the same as Clojure.
