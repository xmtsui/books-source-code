ASM_PATH = "asm-all-3.3.jar"
SEP = File::PATH_SEPARATOR

jar_file, additional_classpath = ARGV
raise "usage: check_jar <jar_file> <additional_classpath>" unless jar_file

`jar tf #{jar_file}`.lines.grep(/\.class$/i).
  map {|s| s.chomp[0..-7].gsub '/', '.'}.
  each do |c|
    puts "checking #{c}"
    `java -cp #{[ASM_PATH, jar_file, additional_classpath].compact.join(SEP)} org.objectweb.asm.util.CheckClassAdapter #{c}`
  end
