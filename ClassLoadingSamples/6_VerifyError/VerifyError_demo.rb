require 'rubygems'
require 'bitescript'

include BiteScript

builder = FileBuilder.build(__FILE__) do
  public_class 'VerifyErrorDemo' do
    public_static_method :test, [], void do
      pop
      returnvoid
    end
  end
end

builder.generate do |filename, class_builder|
  File.open(filename, 'wb') do |file|
    file.write(class_builder.generate)
  end
end
