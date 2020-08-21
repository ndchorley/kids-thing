(ns kids-thing.core)

(defn respond [event]
   (let [input (.-value (js/document.getElementById "input"))]
     (set!
      (.-innerHTML (js/document.getElementById "response"))
      input)))

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
