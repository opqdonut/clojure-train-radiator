Clojure Train Radiator
======================

A small example project in Clojure. Fetches train information from
[rata.digitraffic.fi](https://rata.digitraffic.fi/) API and displays
it.

Getting started
===============

1. Install the Clojure build tool [Leiningen](https://leiningen.org).
2. Run `lein ring server` to start the server and a browser
4. Change the code
5. Refresh the browser and see your changes

Project structure
=================

- `project.clj`: the configuration file for Leiningen
- `src/handler.clj`: the code is here
- `resources/public/style.css`: the CSS style file
- `test/handler_test.clj`: unit tests (run these with `lein test`)

Useful documentation
====================

- [ClojureDocs](https://clojuredocs.org) – the Clojure standard library documentation, searchable
- [Compojure wiki](https://github.com/weavejester/compojure/wiki) – the web framework used in this project
- [Hiccup wiki](https://github.com/weavejester/hiccup/wiki) - how to generate HTML
- [clj-time](https://github.com/clj-time/clj-time) - how to handle time

Tasks
=====

- make the page prettier
- make the station configurable
- show only time table rows for this station
- only show future events
- collect statistics of late trains

Interactive development
=======================

Interactive development means having a REPL (command prompt) where you
can interact with the code and try out things, while also running the
server.

Here's how:

1. Start an interactive clojure environment, for example:
   - run `lein repl` in a terminal
   - use `M-x cider-jack-in` in Emacs (after installing [cider](https://github.com/clojure-emacs/cider))
   - install a Clojure IDE like [Cursive for IntelliJ](https://cursive-ide.com/userguide/)
2. Run `(run)` to start a server
3. Try out things and edit code
4. Reload code changes with either
   - the reload functionality in your IDE
   - running `(refresh)` in the repl

Where to go from here?
======================

TODO
