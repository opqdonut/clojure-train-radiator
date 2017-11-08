Clojure Train Radiator
======================

A small example project in Clojure. Fetches train information from
[rata.digitraffic.fi](https://rata.digitraffic.fi/) API and displays
it.

Getting started
===============

1. Install the Clojure build tool [Leiningen](https://leiningen.org).
2. Run `lein ring server-headless` to start the server
3. Go to http://localhost:3000 in your browser
4. Change the code
5. Refresh the browser and see your changes

Project structure
=================

- `project.clj`: the configuration file for Leiningen
- `src/handler.clj`: the code is here

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

Where to go from here?
======================

TODO
