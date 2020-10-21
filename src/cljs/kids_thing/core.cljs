(ns kids-thing.core)

(def mode (atom :respond))

(defn next-mode [current-mode]
  (if (= current-mode :respond) :next :respond))

(defn get-question [] (.-innerHTML (js/document.getElementById "question")))

(defn get-answer [] (.-value (js/document.getElementById "input")))

(defn get-response []
  (def questions-to-responses
    {"What is your name?" (fn [answer] (str "Hi " answer))})

  (let [answer (get-answer)
        question (get-question)
        respond (questions-to-responses question)]
    (respond answer)))

(defn respond [event]
  (set! (.-textContent (js/document.getElementById "submit-button"))
        (if (= (deref mode) :respond) "Next" "Click me"))

  (set!
   (.-innerHTML (js/document.getElementById "response"))
   (get-response))

  (swap! mode next-mode))

(.addEventListener
 (js/document.getElementById "submit-button")
 "click"
 respond
 false)

