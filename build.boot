(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"html"}

 :dependencies '[[adzerk/boot-cljs "1.7.228-2"]
                 [org.clojure/clojurescript "1.10.764"]])

(require '[adzerk.boot-cljs :refer [cljs]])

(deftask deps [])
