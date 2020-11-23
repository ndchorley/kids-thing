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

(defn respond-to-colour [colour]
  (case (string/lower-case colour)
    "blue" {:text "Like a cornflower"
            :image "https://live.staticflickr.com/1530/24857917311_e7e5c13266_b.jpg"}
    "red" {:text "Like a strawberry"
           :image "https://live.staticflickr.com/2884/9232635422_be3a8830b8_b.jpg"}
    "green" {:text "Like grass"
             :image "https://live.staticflickr.com/658/23163935546_805370060a_b.jpg"}
    "yellow" {:text "Like a lemon"
              :image "https://live.staticflickr.com/1660/24579753002_055acccb0b_b.jpg"}
    "orange" {:text "Like, well, an orange"
              :image "https://live.staticflickr.com/1288/4673819990_740d801fef_b.jpg"}
    "white" {:text "Like snow"
             :image "https://live.staticflickr.com/7675/16662196134_44e1bf8836_b.jpg"}
    "purple" {:text "Like lavender"
              :image "https://live.staticflickr.com/4043/4673298431_897aab6b32_b.jpg"}
    "brown" {:text "Like a bear"
             :image "https://live.staticflickr.com/65535/48085282968_f46a171c8c_b.jpg"}
    {:text "Hmm?" :image nil}))

(defn respond-to-age [age]
  (let [next-years-age
        (case (string/lower-case age)
          ("one" "1") "two"
          ("two" "2") "three"
          ("three" "3") "four"
          ("four" "4") "five"
          ("five" "5") "six"
          ("six" "6") "seven"
          ("seven" "7") "eight"
          ("eight" "8") "nine"
          ("nine" "9") "ten"
          ("ten" "10") "eleven"
          true "Hmm?")]
    {:text (str "You will be " next-years-age " next year")
     :image "https://live.staticflickr.com/2302/2052055757_4e13e12c03_z.jpg"}))

(defn respond-to-game [game]
  {:text "One day, your uncle will teach you how to play chess"
   :image "https://upload.wikimedia.org/wikipedia/commons/3/33/Dubrovnik_chess_set.jpg"})

(def questions-to-responses
  (atom
   (list {:question "What is your name?"
          :responder respond-to-name}
         {:question "How old are you?"
          :responder respond-to-age}
         {:question "What is your favourite colour?"
          :responder respond-to-colour}
         {:question "What is your favourite game?"
          :responder respond-to-game})))

(def final-response
  (let [hour-of-day (.getHours (new js/Date))]
    (if
      (< hour-of-day 18)
      {:text "Have a nice day!" :image "https://live.staticflickr.com/3769/9426404094_054df2e3d4_b.jpg"}
      {:text "Good night!" :image "https://live.staticflickr.com/3944/15595823515_19c9fecd42_b.jpg"})))
