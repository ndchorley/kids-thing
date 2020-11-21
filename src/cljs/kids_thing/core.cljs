(ns kids-thing.core)

(def mode (atom :respond))

(defn next-mode [current-mode]
  (if (= current-mode :respond) :next :respond))

(defn get-answer [] (.-value (js/document.getElementById "input")))

(def questions-to-responses
  (atom
   (list {:question "What is your name?"
          :responder (fn [name]
                       {:text (str "Hi " name)
                        :image "https://live.staticflickr.com/7491/16296571715_b3ebde1e38_b.jpg"})})))

(defn get-responder []
  ((peek (deref questions-to-responses)) :responder))

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
  (show-text ((get-response) :text) "response")
  (show-image ((get-response) :image))
  (swap-button-text)
  (swap! mode next-mode))

(defn get-next-question []
  (swap! questions-to-responses pop)
  (let [questions-remaining (deref questions-to-responses)]
    (if (empty? questions-remaining)
      "Have a nice day!"
      ((peek questions-remaining) :question))))

(defn clear-input []
  (set!
   (.-value (js/document.getElementById "input"))
   ""))

(defn next-question []
  (show-text (get-next-question) "question")
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

