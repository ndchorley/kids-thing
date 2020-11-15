(ns kids-thing.core)

(def mode (atom :respond))

(defn next-mode [current-mode]
  (if (= current-mode :respond) :next :respond))

(defn get-question [] (.-innerHTML (js/document.getElementById "question")))

(defn get-answer [] (.-value (js/document.getElementById "input")))

(defn get-response []
  (def questions-to-responses
    {"What is your name?" (fn [answer] (str "Hi " answer))})

  (let [question (get-question)
        answer (get-answer)
        respond (questions-to-responses question)]
    (respond answer)))

(defn show-response [response]
  (set!
   (.-innerHTML (js/document.getElementById "response"))
   response))

(defn swap-button-text []
  (set! (.-textContent (js/document.getElementById "submit-button"))
        (if (= (deref mode) :respond) "Next" "Click me")))

(defn respond []
  (show-response (get-response))
  (swap-button-text)
  (swap! mode next-mode))

(defn show-next-question []
  (show-response "Next question please!")
  (swap-button-text)
  (swap! mode next-mode))

(.addEventListener
 (js/document.getElementById "submit-button")
 "click"
 (fn [event]
   (let [current-mode (deref mode)]
     (cond
       (= current-mode :respond) (respond)
       (= current-mode :next) (show-next-question))))
 false)

