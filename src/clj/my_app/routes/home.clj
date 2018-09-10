(ns my-app.routes.home
  (:require [my-app.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [ring.util.response :refer [redirect]]
            [clojure.java.io :as io]))

(def todos (atom []))

(defn delete-all []
  (reset! todos []))

(defn home-page [todos]
  (layout/render
    "home.html" {:todos todos}))

(defn create-todo [todo]
  (when (clojure.string/blank? todo)
    (throw (ex-info "invalid todo" {:todo todo})))
  (swap! todos conj todo)
  )

(defroutes home-routes
  (GET "/" [] (home-page @todos))
  (POST "/todos" [todo] 
        (create-todo todo)
        (redirect "/")
        ))

