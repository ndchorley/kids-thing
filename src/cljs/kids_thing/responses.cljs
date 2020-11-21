(ns kids-thing.responses)

(def questions-to-responses
  (atom
   (list {:question "What is your name?"
          :responder (fn [name]
                       {:text (str "Hi " name)
                        :image "https://live.staticflickr.com/7491/16296571715_b3ebde1e38_b.jpg"})})))
