(ns kids-thing.core)

(def mode (atom :respond))

(defn next-mode [current-mode]
  (if (= current-mode :respond) :next :respond))


(defn respond [event]
  (println mode)
  
  (let [input (.-value (js/document.getElementById "input"))]
    (set!
     (.-innerHTML (js/document.getElementById "response"))
     input))

  (swap! mode next-mode)
  (println mode))

(.addEventListener
 (js/document.getElementById "submit-button")
 "click"
 respond
 false)

(.addEventListener
 js/document
 "DOMContentLoaded"
 (fn [event]
   (set!
    (.-innerHTML (js/document.getElementById "question"))
    "<h1>What is your name?"))
 false)
