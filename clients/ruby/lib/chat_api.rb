require "uri"

class Chat_api
  basePath = "https://www.bitmex.com/api/v1"
  # apiInvoker = APIInvoker

  def self.escapeString(string)
    URI.encode(string.to_s)
  end

  def self.get (start,reverse,count= 100,opts={})
    query_param_keys = [:count,:start,:reverse]

    # set default values and merge with input
    options = {
    :start => start,
      :reverse => reverse,
      :count => count}.merge(opts)

    #resource path
    path = "/chat".sub('{format}','json')

    
    # pull querystring keys from options
    queryopts = options.select do |key,value|
      query_param_keys.include? key
    end
    
    headers = nil
    post_body = nil
    response = Swagger::Request.new(:GET, path, {:params=>queryopts,:headers=>headers, :body=>post_body }).make.body
    response.map {|response|Chat.new(response)}

  end

def self.send (message,opts={})
    query_param_keys = []

    # verify existence of params
    raise "message is required" if message.nil?
    # set default values and merge with input
    options = {
    :message => message}.merge(opts)

    #resource path
    path = "/chat".sub('{format}','json')

    
    # pull querystring keys from options
    queryopts = options.select do |key,value|
      query_param_keys.include? key
    end
    
    headers = nil
    post_body = nil
    response = Swagger::Request.new(:POST, path, {:params=>queryopts,:headers=>headers, :body=>post_body }).make.body
    Chat.new(response)

  end

end

