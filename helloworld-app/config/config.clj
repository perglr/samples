(ns config
  (:require [net.cgrand.enlive-html :as html]
            [io.pedestal.app-tools.compile :as compile]))

(def configs
  {:helloworld-app
   {:build {:watch-files (compile/html-files-in "templates")
            ;; When an HTML file changes, trigger the compilation of
            ;; any files which use macros to read in templates. 
            :triggers {:html [#"helloworld_app/app.js" #"greeting/app.js"]}}
    :application {:generated-javascript "generated-js"
                  :api-server {:host "localhost" :port 8080 :log-fn nil}
                  :default-template "application.html"
                  :output-root :public}
    :control-panel {:design {:uri "/design.html"
                             :name "Design"
                             :order 0}}
    :aspects {:development {:uri "/helloworld-app-dev.html"
                            :name "Development"
                            :out-file "helloworld-app-dev.js"
                            :scripts ["goog.require('helloworld_app.app');"
                                      "helloworld_app.app.main();"]
                            :order 1}
              :fresh {:uri "/fresh.html"
                      :name "Fresh"
                      :out-file "fresh.js"
                      :scripts ["goog.require('io.pedestal.app.net.repl_client');"
                                "io.pedestal.app.net.repl_client.repl();"]
                      :order 2
                      :output-root :tools-public}
              :production {:uri "/helloworld-app.html"
                           :name "Production"
                           :optimizations :advanced
                           :out-file "helloworld-app.js"
                           :scripts ["helloworld_app.app.main();"]
                           :order 3}
              :greeting {:uri "/greeting-dev.html"
                         :name "Greeting"
                         :out-file "greeting-dev.js"
                         :scripts ["goog.require('greeting.app');"
                                   "greeting.app.main();"]
                         :order 4}}}})
