(ns kids-thing.core)

(def mode (atom :respond))

(defn next-mode [current-mode]
  (if (= current-mode :respond) :next :respond))

(defn get-answer [] (.-value (js/document.getElementById "input")))

(def questions-to-responses
  (list {:question "What is your name?"
         :responder (fn [name] (str "Hi " name))}))

(defn get-response []
  (let [answer (get-answer)
        respond ((peek questions-to-responses) :responder)]
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

