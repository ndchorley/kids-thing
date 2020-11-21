(ns kids-thing.responses)

(defn respond-to-name [name]
  (let [hour-of-day (.getHours (new js/Date))]
    (cond
      (< hour-of-day 12) {:text (str "Good morning " name)
                          :image "https://live.staticflickr.com/7491/16296571715_b3ebde1e38_b.jpg"}

      (< hour-of-day 17) {:text (str "Good afternoon " name)
                          :image "https://live.staticflickr.com/65535/49611913502_dde5b04bbe_b.jpg"}
      true {:text (str "Good evening " name)
            :image "https://live.staticflickr.com/65535/48763561263_6707d9fcb2_b.jpg"})))

(def questions-to-responses
  (atom
   (list {:question "What is your name?"
          :responder respond-to-name})))
