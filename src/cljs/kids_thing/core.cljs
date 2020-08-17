(ns kids-thing.core)

(enable-console-print!)


(.addEventListener
 (js/document.getElementById "submit-button")
 "click"
 (fn [event]
   (println
    (.-value (js/document.getElementById "input"))))
 false)
