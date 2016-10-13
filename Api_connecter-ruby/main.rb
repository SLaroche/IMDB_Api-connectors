require 'net/http'
require 'uri'
require 'json'

uri = URI('http://www.omdbapi.com/?s=' + ARGV[0] +'&y=&plot=short&r=json')
res = Net::HTTP.get(uri)
json = JSON.parse(res)
puts "Title: " + json["Search"][0]["Title"]
puts "Year: " +json["Search"][0]["Year"]
