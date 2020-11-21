(ns kids-thing.core
  (:require [kids-thing.responses :as responses]))

(def mode (atom :respond))

(defn next-mode [current-mode]
  (if (= current-mode :respond) :next :respond))

(defn set-mode [new-mode] (reset! mode new-mode))

(defn get-answer [] (.-value (js/document.getElementById "input")))

(defn get-responder []
  ((peek (deref responses/questions-to-responses)) :responder))

(defn get-response []
  (let [answer (get-answer) respond (get-responder)]
    (respond answer)))

(defn show-text [what where]
  (set!
   (.-innerHTML (js/document.getElementById where))
   what))

(defn show-image [url]
  (let [element (js/document.createElement "img")]
    (.setAttribute element "src" url)
    (.append (js/document.getElementById "image") element)))

(defn set-button-text [text]
  (set!
   (.-textContent (js/document.getElementById "submit-button"))
   text))

(defn respond [response]
  (let [{:keys [text image]} response]
    (show-text text "response")
    (if image (show-image image)))

  (set-button-text "Next")
  (set-mode :next))

(defn get-next-question []
  (swap! responses/questions-to-responses pop)
  (let [questions-remaining (deref responses/questions-to-responses)]
    (if (seq questions-remaining)
      ((first questions-remaining) :question) nil)))

(defn clear-input []
  (set!
   (.-value (js/document.getElementById "input"))
   ""))

(defn clear-image []
  (.remove (first (js/document.getElementsByTagName "img"))))

(defn clear-response []
  (clear-image)
  (show-text "" "response"))

(defn show-question [question]
  (show-text question "question"))

(defn clear-question [] (show-text "" "question"))

(defn disable-button []
  (.setAttribute
   (js/document.getElementById "submit-button")
   "disabled"
   true))

(defn next-question []
  (let [question (get-next-question)]
    (if question
      (do
        (show-question question)
        (clear-input)
        (clear-response)
        (set-button-text "Click me")
        (set-mode :respond))

      (do
        (clear-input)
        (clear-question)
        (clear-response)
        (disable-button)
        (respond responses/final-response)))))

(.addEventListener
 (js/document.getElementById "submit-button")
 "click"
 (fn [event]
   (let [current-mode (deref mode)]
     (cond
       (= current-mode :respond) (respond (get-response))
       (= current-mode :next) (next-question))))
 false)
