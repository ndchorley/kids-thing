(ns kids-thing.core
  (:require [kids-thing.responses :as responses]))

(def mode (atom :respond))

(defn next-mode [current-mode]
  (if (= current-mode :respond) :next :respond))

(defn get-answer [] (.-value (js/document.getElementById "input")))

(defn get-responder []
  ((peek (deref responses/questions-to-responses)) :responder))

(defn get-response []
  (let [answer (get-answer)
        respond (get-responder)]
    (respond answer)))

(defn show-text [what where]
  (set!
   (.-innerHTML (js/document.getElementById where))
   what))

(defn show-image [url]
  (let [element (js/document.createElement "img")]
    (.setAttribute element "src" url)
    (.append (js/document.getElementById "image") element)))

(defn swap-button-text []
  (set! (.-textContent (js/document.getElementById "submit-button"))
        (if (= (deref mode) :respond) "Next" "Click me")))

(defn respond []
  (let [{:keys [text image]} (get-response)]
    (show-text text "response")
    (show-image image))

  (swap-button-text)
  (swap! mode next-mode))

(defn get-next-question []
  (swap! responses/questions-to-responses pop)
  (let [questions-remaining (deref responses/questions-to-responses)]
    (if (seq questions-remaining) (questions-remaining :question) nil)))

(defn clear-input []
  (set!
   (.-value (js/document.getElementById "input"))
   ""))

(defn next-question []
  (let [question (get-next-question)]
    (if
      question
      (show-text question "question")
      (show-text "Have a nice day!" "question")))

  (show-text "" "response")
  (clear-input)
  (swap-button-text)
  (swap! mode next-mode))

(.addEventListener
 (js/document.getElementById "submit-button")
 "click"
 (fn [event]
   (let [current-mode (deref mode)]
     (cond
       (= current-mode :respond) (respond)
       (= current-mode :next) (next-question))))
 false)

