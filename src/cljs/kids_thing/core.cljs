(ns kids-thing.core)

(def mode (atom :respond))

(defn next-mode [current-mode]
  (if (= current-mode :respond) :next :respond))

(defn respond [event]
  (set! (.-textContent (js/document.getElementById "submit-button"))
        (if (= (deref mode) :respond) "Next" "Click me"))

  (let [input (.-value (js/document.getElementById "input"))]
    (set!
     (.-innerHTML (js/document.getElementById "response"))
     (str "Hi " input)))

  (swap! mode next-mode))

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
    "<h1>What is your name?</h1>"))
 false)
