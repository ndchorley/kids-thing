(ns kids-thing.core)

(.addEventListener
 (js/document.getElementById "submit-button")
 "click"
 (fn [event]
   (let [input (.-value (js/document.getElementById "input"))]
     (set!
      (.-innerHTML (js/document.getElementById "response"))
      input)))
 false)
