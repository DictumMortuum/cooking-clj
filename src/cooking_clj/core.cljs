(ns cooking-clj.core
    (:require [om.core :as om :include-macros true]
              [om.dom :as dom :include-macros true]))

; (enable-console-print!)

(def recipes '("Μπριζόλα με σαλάτα"
               "Παστίτσιο με σαλάτα"
               "Καλαμαράκια με σαλάτα"
               "Μακαρόνια με τυρί και σάλτσα"
               "Γεμιστά"
               "Αυγά τηγανιτά με τυρί και ψωμί"
               "Γίγαντες με τυρί και ψωμί"
               "Κοτόπουλο με σαλάτα"
               "Ομελέτα με ψωμί"
               "Ψάρι ψητό με σαλάτα"
               "Σαλάτα χωριάτικη με ψωμί"
               "Σαλάτα με τόνο και ψωμί"
               "Μπιφτέκια με σαλάτα"))

(defonce app-state (atom {:recipe (rand-nth recipes)}))

(defn get-recipe []
  (swap! app-state assoc :recipe (rand-nth recipes)))

(defn absolute-center [state]
  (dom/div #js {:style #js {:position "absolute"
                            :left "50%"
                            :top "50%"
                            :transform "translate(-50%, -50%)"}}
               state))

(defn typography [state]
  (dom/span #js {:style #js {:fontSize "2em"
                             :lineHeight "2"
                             :fontFamily "sans-serif"}
                 :onClick get-recipe}
                state))

(defn recipes-view [data owner]
  (reify om/IRender
    (render [this]
      (dom/div nil
               (absolute-center (typography (:recipe data)))))))

(om/root recipes-view app-state
  {:target (. js/document (getElementById "app"))})

(defn on-js-reload [])
