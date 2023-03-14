# TowersSearchAI

This is an AI project demonstrating uses of multiple search algorithms in order to find a path to the destination
Written in java

Usage of pom plugin to make jar excecutable:
```
<plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>shade</goal>
                        </goals>
                        <configuration>
                            <shadedArtifactAttached>true</shadedArtifactAttached>
                            <transformers>
                                <transformer implementation=
                                                     "org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                    <mainClass>Your_main_class_here</mainClass>
                                </transformer>
                            </transformers>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
```

Then do mvn clean then run app then do mvn install and done, also make sure to make a Main class and link it to the real program starting point
