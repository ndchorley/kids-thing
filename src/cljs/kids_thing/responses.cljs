(ns kids-thing.responses (:require [clojure.string :as string]))

(defn respond-to-name [name]
  (let [hour-of-day (.getHours (new js/Date))
        capitalised-name (string/capitalize name)]
    (cond
      (< hour-of-day 12) {:text (str "Good morning " capitalised-name)
                          :image "https://live.staticflickr.com/7491/16296571715_b3ebde1e38_b.jpg"}

      (< hour-of-day 17) {:text (str "Good afternoon " capitalised-name)
                          :image "https://live.staticflickr.com/65535/49611913502_dde5b04bbe_b.jpg"}
      true {:text (str "Good evening " capitalised-name)
            :image "https://live.staticflickr.com/65535/48763561263_6707d9fcb2_b.jpg"})))

(def questions-to-responses
  (atom
   (list {:question "What is your name?"
          :responder respond-to-name})))

(def final-response
  (let [hour-of-day (.getHours (new js/Date))]
    (if
      (< hour-of-day 18)
      {:text "Have a nice day!" :image "https://live.staticflickr.com/3769/9426404094_054df2e3d4_b.jpg"}
      {:text "Good night!" :image "https://live.staticflickr.com/3944/15595823515_19c9fecd42_b.jpg"})))
