(ns pulsar-example.core
  (:require
   [co.paralleluniverse.pulsar
    [core :refer :all]
    [actors :refer :all]])
  (:refer-clojure :exclude [promise await])
  (:gen-class))

(defsfn ping [n pong]
  (if (== n 0)
    (do
      (! pong :finished)
      (println "ping finished"))
    (do
      (! pong [:ping @self])
      (receive
        :pong (println "Ping received pong"))
      (recur (dec n) pong))))

(defsfn pong []
  (receive
    :finished (println "Pong finished")
    [:ping ping] (do
                   (println "Pong received ping")
                   (! ping :pong)
                   (recur))))

(defn -main []
  (let [a1 (spawn pong)
        b1 (spawn ping 3 a1)]
    (join a1)
    (join b1)
    :ok))
