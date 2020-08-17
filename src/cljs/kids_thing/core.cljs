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
