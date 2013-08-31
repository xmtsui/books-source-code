require 'rubygems'
require 'bitescript'

include BiteScript

IFoo    = Java::IFoo
FooImpl = Java::FooImpl
Bar     = Java::Bar

fb = FileBuilder.build(__FILE__) do
  public_class 'TestInterfaceCall' do
    public_static_method 'main', [], void, string[] do
      # IFoo f = new FooImpl();
      new FooImpl
      dup
      invokespecial FooImpl, '<init>', [void]
      astore 1

      # f.method();
      aload 1
      invokeinterface IFoo, 'method', [void]
      
      # Bar b = new Bar();
      new Bar
      dup
      invokespecial Bar, '<init>', [void]
      astore 2
      
      # ((IFoo)b).method();
      aload 2
      ## checkcast IFoo # skip the cast to trigger IncompatibleClassChangeError
      invokeinterface IFoo, 'method', [void]
      returnvoid
    end
  end
end

fb.generate do |filename, class_builder|
  File.open(filename, 'w') do |file|
    file.write(class_builder.generate)
  end
end
